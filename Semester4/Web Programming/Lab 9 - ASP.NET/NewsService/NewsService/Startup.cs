using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(NewsService.Startup))]
namespace NewsService
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
